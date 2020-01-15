(ns atmos-tokens.serializers
  (:require [atmos-kernel.serializer.core :refer :all]
            [atmos-kernel.core :refer [keyword-map]]))


(defrecord Token [type value])
(defrecord TokenValidation [token extra-data])


(defn serialize-token
  [token-data]
  (let [{:keys [type value]} token-data
        token-data (mapping (make-fields ->Token serializer-field [[type 'type]
                                                                   [value 'value]]))]
    token-data))

(defn de-serialize-token-validation
  [token-validation-data]
  (mapping (->TokenValidation (de-serializer-field (get token-validation-data "token") :string)
                              (keyword-map (get token-validation-data "extra_data")))))