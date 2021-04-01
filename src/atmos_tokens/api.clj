(ns atmos-tokens.api
  (:require [atmos-web-kernel-reitit.core :as web-core]
            [atmos-web-kernel-reitit.exception :refer [handle-exception]]
            [atmos-kernel.serializer.core :refer [serialize de-serialize]]
            [atmos-tokens.core :refer :all]
            [atmos-tokens.specs :refer :all]))

(def tokens "tokens")
(def validate "validate")

(def routes [(str "/" tokens)
             ["" {:options (web-core/web-request
                             (fn [_] {:name        tokens
                                      :description "Atmos micro service for tokens"}))

                  :post    (web-core/web-request
                             (fn [{:keys [body-params] :as request}]
                               (try
                                 (let [token-request-data (de-serialize body-params de-serialize-token-map)
                                       token-request-data (assoc token-request-data :request request)]

                                   (serialize (generate-token token-request-data) serialize-token-map))
                                 (catch Exception e (handle-exception e request)))))}]

             [(str "/" validate)
              {:post (web-core/web-request
                       (fn [{:keys [body-params] :as request}]
                         (try
                           (let [token-request-data (de-serialize body-params de-serialize-token-validation-map)
                                 token-request-data (assoc token-request-data :request request)]

                             (validate-token token-request-data))
                           (catch Exception e (handle-exception e request)))))}]])


(def app
  (let [router (web-core/web-router routes)
        default-handler (web-core/web-request (fn [_] "Route not found."))]
    (web-core/ring-app router default-handler)))
