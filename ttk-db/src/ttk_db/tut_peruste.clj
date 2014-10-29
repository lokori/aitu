(ns ttk-db.tut-peruste)

; pohjana Excel-taulukko  http://www.oph.fi/download/158907_Peruslista_10_7_14_www.xlsx
; per rivi käytetty kaavaa =CONCATENATE("""";I309;""" {:peruste-diaari """;L309;"""}")
(def tut-perusteet
 {
"354301" {:peruste-diaari "7/011/2009"}
})

(defn generoi-sql []
  (println "set session aitu.kayttaja='INTEGRAATIO';")
  (println)
  (doseq [tutv tut-perusteet ]
    (let [tutkinto (first tutv)
          diaarinumero (:peruste-diaari (second tutv))]
      (println (str "insert into tutkintoversio (tutkintotunnus, versio, koodistoversio, peruste, hyvaksytty, voimassa_alkupvm, voimassa_loppupvm, siirtymaajan_loppupvm, jarjestyskoodistoversio, osajarjestyskoodisto) "
                "  select tutkintotunnus, 2 as versio, koodistoversio, '" diaarinumero "' as peruste, hyvaksytty, (select DATE 'yesterday'), voimassa_loppupvm, siirtymaajan_loppupvm, jarjestyskoodistoversio, osajarjestyskoodisto from tutkintoversio "
                "    where tutkintotunnus = '" tutkinto "' and versio = 1;"))
      (println (str "update tutkintoversio set voimassa_loppupvm = (select DATE 'yesterday') where tutkintotunnus='" tutkinto "' and versio=1;"))
      (println (str "update nayttotutkinto set uusin_versio_id = (select tutkintoversio_id from tutkintoversio where versio = 2 and tutkintotunnus = '" tutkinto "') where tutkintotunnus = '" tutkinto "';"))
      (println)
      )))
