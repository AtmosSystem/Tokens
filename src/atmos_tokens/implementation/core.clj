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

(defn- query-token
  [entity]
  (let [credentials (:credentials configuration)
        access-token (:token configuration)
        project-id (-> configuration :datastore :project-id)
        namespace (-> configuration :datastore :namespace)]
    (with-token credentials access-token
                (query project-id token :filters {:Entity [= entity]}))))


(extend-type String
  ITokenProtocol
  (get-token [entity] (query-token entity)))





