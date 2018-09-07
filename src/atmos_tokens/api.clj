(ns atmos-tokens.api
  (:require [atmos-kernel.web.core :refer [json-web-app
                                           request-body]]
            [atmos-kernel.web.route :refer [defatmos-route
                                            atmos-GET]]
            [atmos-kernel.web.security.auth :refer [basic-auth]]
            [atmos-tokens.core :refer :all]))

(def token "token")

(defatmos-route app-routes :tokens
                (atmos-GET [token :entity]
                           (get-token entity)
                           :authentication-needed? true))

(def app (json-web-app app-routes basic-auth))