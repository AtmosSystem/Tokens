(ns atmos-tokens.specs
  (:require [clojure.spec.alpha :as s]
            [atmos-kernel.serializer.core :refer [is-valid-serializer-map? is-valid-de-serializer-map?]]))

(s/def ::type string?)
(s/def ::entity string?)
(s/def ::token string?)
(s/def ::validation-type string?)
(s/def ::extra-data (s/map-of keyword? any?))

(s/def ::token-spec (s/keys :req-un [::type ::entity ::extra-data]))
(s/def ::token-validation-spec (s/keys :req-un [::token ::validation-type ::extra-data]))

(def serialize-token-map {:type        :type
                          :access_type :access-type
                          :token       :token})

; Map to de serialize token data.
(def de-serialize-token-map {:data-spec ::token-spec
                             :fields    {:entity     :entity
                                         :type       :type
                                         :extra-data :extra_data}})

; Map to de serialize token validation data.
(def de-serialize-token-validation-map {:data-spec ::token-validation-spec
                                        :fields    {:token           :token
                                                    :validation-type :validation_type
                                                    :extra-data      :extra_data}})