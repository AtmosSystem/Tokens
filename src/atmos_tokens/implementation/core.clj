(ns atmos-tokens.implementation.core
  (:require [atmos-tokens.core :refer :all]
            [environ.core :refer [env]]
            [atmos-kernel.configuration :refer [read-edn]]
            [clj-google-datastore.core :refer :all]
            [clj-google.auth :refer [with-token]]))

;-------------------------------------------------------
; BEGIN VARS
;-------------------------------------------------------

(def ^:private resource-file (or (keyword (env :resource-file)) :config-prod))
(def ^:private configuration (read-edn resource-file))

;-------------------------------------------------------
; END VARS
;-------------------------------------------------------


(defkind token :Token)

(load "tokens_get")

(extend-type String
  ITokenProtocol
  (get-microservice-token [entity] (query-token entity)))





