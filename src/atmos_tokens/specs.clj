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
(s/def ::utr string?)
(s/def ::extra_data (s/keys :req-un [::utr]))

(s/def ::token-spec (s/keys :req-un [::type ::entity ::extra_data]))

(s/def ::token-validation-spec (s/keys :req-un [::token ::validation_type ::extra_data]))

(def serialize-token-map {:type        :type
                          :access_type :access-type
                          :token       :token})

(defn de-serialize-type
  [token-data property]
  (-> token-data property (string/replace #"_" "-") keyword))

; Map to de serialize token data.
(def de-serialize-token-map {:data-spec ::token-spec
                             :fields    {:entity     :entity
                                         :type       #(de-serialize-type % :type)
                                         :extra-data :extra_data}})

; Map to de serialize token validation data.
(def de-serialize-token-validation-map {:data-spec ::token-validation-spec
                                        :fields    {:token           :token
                                                    :validation-type #(de-serialize-type % :validation_type)
                                                    :extra-data      :extra_data}})