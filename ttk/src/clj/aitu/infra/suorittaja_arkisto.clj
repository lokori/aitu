;; Copyright (c) 2015 The Finnish National Board of Education - Opetushallitus
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

(ns aitu.infra.suorittaja-arkisto
  (:require  [korma.core :as sql]
             [aitu.auditlog :as auditlog]))

(defn hae-kaikki
  []
  (sql/select :suorittaja
    (sql/join :kayttaja (= :kayttaja.oid :muutettu_kayttaja))
    (sql/fields :suorittaja_id :etunimi :sukunimi :hetu :oid :muutettuaika
                [(sql/sqlfn "concat" :kayttaja.etunimi " " :kayttaja.sukunimi) :muutettu_nimi])
    (sql/order :suorittaja_id :DESC)))

(defn lisaa!
  [form]
  (auditlog/suorittaja-operaatio! :lisays (dissoc form :hetu :oid))
  (sql/insert :suorittaja
    (sql/values (select-keys form [:etunimi :sukunimi :hetu :oid]))))

(defn poista!
  [suorittajaid]
  (auditlog/suorittaja-operaatio! :poisto {:suorittajaid suorittajaid})
  (sql/delete :suorittaja
    (sql/where {:suorittaja_id suorittajaid})))

(defn tallenna!
  [suorittajaid suorittaja]
  (auditlog/suorittaja-operaatio! :paivitys (assoc suorittaja :suorittaja_id suorittajaid))
  (sql/update :suorittaja
    (sql/set-fields (select-keys suorittaja [:etunimi :sukunimi :hetu :oid]))
    (sql/where {:suorittaja_id suorittajaid})))
