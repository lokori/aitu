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

(ns aitu.toimiala.voimassaolo.saanto.jasenyys
  (:require [oph.common.util.util :as util]))

(defn ^:private onko-voimassa?
  [jasenyys ttk-voimassaolo]
  (and ttk-voimassaolo (util/pvm-tuleva-tai-tanaan? (or (:loppupvm jasenyys) util/time-forever))))

(defn taydenna-jasenyyden-voimassaolo
  "Täydentää jäsenyyden voimassaolon toimikunta-voimassaolon perusteella"
  [jasenyys toimikunta-voimassaolo]
  (assoc jasenyys :voimassa (onko-voimassa? jasenyys toimikunta-voimassaolo)))
