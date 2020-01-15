(ns atmos-tokens.api
  (:require [atmos-kernel.web.ring :refer [def-json-web-api]]
            [ring.middleware.defaults :refer [api-defaults]]
            [atmos-kernel.web.security.auth :refer [basic-auth]]
            [atmos-kernel.web.route :refer [defatmos-routes
                                            atmos-main-route
                                            atmos-POST]]
            [atmos-kernel.core :refer [keyword-map]]
            [atmos-kernel.serializer.core :refer :all]
            [atmos-tokens.core :refer :all]
            [atmos-tokens.serializers :refer :all]))

(def token "token")
(def validate "validate")

(declare app app-routes request)

(defatmos-routes app-routes
                 (atmos-main-route :tokens)

                 (atmos-POST [token] request
                             (let [token-request-data (keyword-map (request :params))]
                               (serialize (get-token token-request-data) serialize-token)))

                 (atmos-POST [token validate] request
                             (let [token-request-data (de-serialize (request :params) de-serialize-token-validation)]
                               (validate-token token-request-data))))

(def-json-web-api app app-routes api-defaults basic-auth)