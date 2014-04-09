(ns aitu.integraatio.sql.db-validation-test
  (:require
    [aitu.integraatio.sql.validationtest :as validationtest]
    [clojure.test :refer [deftest testing is]]
    [korma.core :as sql]
     [korma.db :as db]
    [aitu.integraatio.sql.test-util :refer :all]))

(defn failed? [kysely]
  (not (empty? (:results kysely))))

(deftest ^:integraatio tarkista-tietokanta-sql-validointi
  (testing "tarkistetaan että tietokannan SQL validoinnit menevät läpi. Ilman konversiodataa lähinnä syntaktinen tarkastus."
    (tietokanta-fixture
      #(let [tulokset (validationtest/run-queries! @validationtest/default-query-list)]
         (is (empty? (filter failed? tulokset)))))))
