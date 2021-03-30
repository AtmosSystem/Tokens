(ns atmos-tokens.specs
  (:require [clojure.spec.alpha :as s]
            [atmos-kernel.serializer.core :refer [is-valid-serializer-map? is-valid-de-serializer-map?]]
            [clojure.string :as string]))

(def token-types ["access_token" "refresh_token"])
(s/def ::token-type (s/and string? (fn [token-type] (some #(= token-type %) token-types))))

(s/def ::type ::token-type)
(s/def ::validation_type ::token-type)
(s/def ::entity string?)
(s/def ::token string?)
(s/def ::extra_data (s/map-of keyword? any?))

(s/def ::token-spec (s/keys :req-un [::type ::entity]
                            :opt-un [::extra_data]))

(s/def ::token-validation-spec (s/keys :req-un [::token ::validation_type]
                                       :opt-un [::extra_data]))

(def serialize-token-map {:type        :type
                          :access_type :access-type
                          :token       :token})

(defn de-serialize-type
  [token-type]
  (-> token-type (string/replace #"_" "-") keyword))

; Map to de serialize token data.
(def de-serialize-token-map {:data-spec ::token-spec
                             :fields    {:entity     :entity
                                         :type       de-serialize-type
                                         :extra-data :extra_data}})

; Map to de serialize token validation data.
(def de-serialize-token-validation-map {:data-spec ::token-validation-spec
                                        :fields    {:token           :token
                                                    :validation-type de-serialize-type
                                                    :extra-data      :extra_data}})