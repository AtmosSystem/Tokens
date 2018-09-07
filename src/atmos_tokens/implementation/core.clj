(ns atmos-tokens.implementation.core
  (:require [atmos-tokens.core :refer :all]
            [atmos-kernel.web.security.auth :refer [IAuthHandlerProtocol]]
            [environ.core :refer [env]]
            [atmos-kernel.configuration :refer [read-edn]]
            [clj-google-datastore.core :refer [defkind query]]
            [clj-google.auth :refer [with-token]])
  (:import (java.util Map)))

;-------------------------------------------------------
; BEGIN VARS
;-------------------------------------------------------

(def ^:private resource-file (or (keyword (env :resource-file)) :config-prod))
(def ^:private configuration (read-edn resource-file))

;-------------------------------------------------------
; END VARS
;-------------------------------------------------------

(extend-type Map
  IAuthHandlerProtocol
  (get-authentication [request auth-data] false))


(defkind token :Token)

(load "tokens_get")

(extend-type String
  ITokenProtocol
  (get-microservice-token [entity] (query-token entity)))





