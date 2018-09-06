(ns atmos-tokens.api
  (:require [atmos-kernel.web.core :refer [json-web-app
                                           request-body]]
            [atmos-kernel.web.route :refer [defatmos-route
                                            atmos-GET]]
            [atmos-kernel.web.security.auth :refer [atmos-auth-backend]]
            [buddy.auth.backends :as backends]
            [atmos-tokens.service :refer :all]))

(def token "token")
(def microservice "microservice")

(defatmos-route app-routes :tokens
                (atmos-GET [token microservice :entity]
                           (get-microservice-token* entity)
                           :authentication? true))

(def app (json-web-app app-routes (atmos-auth-backend
                                    backends/basic {:realm  "API"
                                                    :authfn authfn})))