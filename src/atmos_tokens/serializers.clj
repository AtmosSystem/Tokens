(ns atmos-tokens.serializers
  (:require [atmos-kernel.serializer.core :refer :all]
            [atmos-kernel.core :refer [keyword-map]]))


(defrecord Token [type access-type token])
(defrecord TokenRequest [entity type extra-data])
(defrecord TokenValidation [token extra-data])


(defn serialize-token
  [token-data]
  (let [{:keys [type access-type token]} token-data
        token-data (mapping (make-fields ->Token serializer-field [[type 'type]
                                                                   [access-type 'access_type]
                                                                   [token 'token]]))]
    token-data))

(defn de-serialize-token-request
  [token-request-data]
  (mapping (->TokenRequest (de-serializer-field (get token-request-data "entity"))
                           (de-serializer-field (get token-request-data "type"))
                           (de-serializer-field (get token-request-data "extra_data")))))

(defn de-serialize-token-validation
  [token-validation-data]
  (mapping (->TokenValidation (de-serializer-field (get token-validation-data "token"))
                              (de-serializer-field (get token-validation-data "extra_data")))))