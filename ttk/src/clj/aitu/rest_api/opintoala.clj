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

(ns aitu.rest-api.opintoala
  (:require [compojure.core :as c]
            [cheshire.core :as cheshire]
            [aitu.infra.opintoala-arkisto :as arkisto]
            [oph.common.util.http-util :refer [json-response]]
            [aitu.compojure-util :as cu]
            [korma.db :as db]))

(c/defroutes reitit
  (cu/defapi :yleinen-rest-api nil :get "/haku" [termi]
    (json-response (arkisto/hae-termilla termi)))
  (cu/defapi :yleinen-rest-api nil :get "/" []
    (json-response (arkisto/hae-kaikki)))
  (cu/defapi :yleinen-rest-api nil :get "/:koodi" [koodi]
    (json-response (arkisto/hae koodi))))


