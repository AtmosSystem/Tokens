(ns atmos-tokens.api
  (:require [atmos-web-kernel-reitit.core :as web-core]
            [clojure.tools.logging :as log]
            [atmos-kernel.serializer.core :refer [serialize de-serialize]]
            [atmos-tokens.core :refer :all]
            [atmos-tokens.specs :refer :all]))

(def tokens "tokens")
(def validate "validate")

(def routes [tokens
             [""
              {:options (web-core/web-request
                          (fn [_] {:name        tokens
                                   :description "Atmos micro service for tokens"}))

               :post    (web-core/web-request
                          (fn [{:keys [body-params]} request]
                            (let [token-request-data (de-serialize body-params de-serialize-token-map)
                                  token-request-data (assoc token-request-data :request request)]

                              (serialize (generate-token token-request-data) serialize-token-map))))}]

             [validate
              {:post (web-core/web-request
                       (fn [{:keys [body-params]} request]
                         (let [token-request-data (de-serialize body-params de-serialize-token-validation-map)
                               token-request-data (assoc token-request-data :request request)]

                           (validate-token token-request-data))))}]])


(def app
  (let [router (web-core/web-router routes)
        default-handler (web-core/web-request (fn [_] "Route not found."))]
    (do
      (log/info "Service started")
      (web-core/ring-app router default-handler))))
