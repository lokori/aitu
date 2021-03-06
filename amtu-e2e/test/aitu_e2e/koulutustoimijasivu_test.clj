;; Copyright (c) 2013 The Finnish National Board of Education - Opetushallitus
;;
;; This program is free software:  Licensed under the EUPL, Version 1.1 or - as
;; soon as they will be approved by the European Commission - subsequent versions
;; of the EUPL (the "Licence");
;;
;; You may not use this work except in compliance with the Licence.
;; You may obtain a copy of the Licence at: http://www.osor.eu/eupl/
;;
;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; European Union Public Licence for more details.

(ns aitu-e2e.koulutustoimijasivu-test
  (:require [clojure.set :refer [subset?]]
            [clojure.test :refer [deftest is testing]]
            [clj-webdriver.taxi :as w]
            [aitu-e2e.util :refer :all]
            [aitu-e2e.aitu-util :refer :all]
            [aitu-e2e.data-util :refer [with-data
                                        menneisyydessa
                                        tulevaisuudessa
                                        menneisyydessa-kayttoliittyman-muodossa
                                        tulevaisuudessa-kayttoliittyman-muodossa
                                        aseta-jarjestamissopimus-paattyneeksi]]
            [aitu-e2e.datatehdas :as dt]
            [clj-time.core :as time]
            [clj-time.format :as time-format]
            [aitu-e2e.oppilaitossivu-test :refer [oppilaitossivu-sopimukset-data]]))

(defn koulutustoimijasivu [id] (str "/fi/#/koulutustoimija/" id "/tiedot"))

(def nayta-vanhat-selector "a[ng-click='naytaVanhatSopimukset = !naytaVanhatSopimukset']")

(defn sopimuslista [lista]
  (into #{}
        (for [elements (w/find-elements {:css (str lista " tbody tr")})]
          (clojure.string/split (w/text elements) #"\n"))))

(defn koulutustoimijan-tieto [binding]
  (-> *ng*
      (.binding binding)
      (w/find-elements)
      (first)
      (w/text)))

(deftest koulutustoimijasivu-test []
  (with-webdriver
    ;; Oletetaan, että
    (let [puhelin "040123456"
          sahkoposti "koulutustoimija@koulutustoimija.fi"
          osoite "Koulutustoimijakatu 1"
          postinumero "12345"
          postitoimipaikka "Postitoimipaikka"
          koulutustoimija (merge (dt/setup-koulutustoimija) {:puhelin puhelin
                                                             :sahkoposti sahkoposti
                                                             :osoite osoite
                                                             :postinumero postinumero
                                                             :postitoimipaikka postitoimipaikka})
          tunnus (:ytunnus koulutustoimija)]
      (with-data {:koulutustoimijat [koulutustoimija]}
        (testing "pitäisi näyttaa tiedot koulutustoimijasta"
          ;; Kun
          (avaa (koulutustoimijasivu tunnus))
          ;; Niin
          (is (= (sivun-otsikko) (clojure.string/upper-case (:nimi_fi koulutustoimija))))
          (is (= (koulutustoimijan-tieto "koulutustoimija.puhelin") puhelin))
          (is (= (koulutustoimijan-tieto "koulutustoimija.sahkoposti") sahkoposti))
          (is (= (koulutustoimijan-tieto "koulutustoimija.osoite") osoite))
          (is (= (koulutustoimijan-tieto "koulutustoimija.postitoimipaikka") (str postinumero " " postitoimipaikka))))))))

(deftest koulutustoimijasivu-sopimukset-test []
  (with-webdriver
    ;; Oletetaan, että
    (let [y-tunnus "0000000-0"
          oppilaitostunnus "12345"
          testidata (oppilaitossivu-sopimukset-data y-tunnus oppilaitostunnus)
          testitutkinto_nimi (:nimi_fi (first (:tutkinnot testidata)))
          vanhentuva-sopimus (get-in testidata [:jarjestamissopimukset 1])
          vanhentuva-sopnro (:sopimusnumero vanhentuva-sopimus)
          ei-vanhentuva-sopimus (get-in testidata [:jarjestamissopimukset 0])
          ei-vanhentuva-sopnro (:sopimusnumero ei-vanhentuva-sopimus)]
      (with-data testidata
        (testing "pitäisi näyttaa listoissa uudet ja vanhat koulutustoimijan sopimukset"
          (aseta-jarjestamissopimus-paattyneeksi vanhentuva-sopimus)
          ;; Kun
          (avaa (koulutustoimijasivu y-tunnus))
          (w/click nayta-vanhat-selector)
          ;; Niin
          (is (= #{[ei-vanhentuva-sopnro testitutkinto_nimi (str menneisyydessa-kayttoliittyman-muodossa " – " tulevaisuudessa-kayttoliittyman-muodossa)]}
                 (sopimuslista ".nykyiset-sopimukset")))
          (is (= #{[vanhentuva-sopnro testitutkinto_nimi (str menneisyydessa-kayttoliittyman-muodossa " – " menneisyydessa-kayttoliittyman-muodossa)]}
                 (sopimuslista ".vanhat-sopimukset"))))))))
