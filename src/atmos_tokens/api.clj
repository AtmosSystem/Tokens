(ns atmos-tokens.api
  (:require [atmos-kernel.web.core :refer [json-web-app]]
            [atmos-kernel.web.core :refer [request-body]]
            [atmos-kernel.web.route :refer [defatmos-route
                                            atmos-GET]]
            [atmos-tokens.service :refer :all]))

(def token "token")

(defatmos-route app-routes :tokens
                (atmos-GET [token :entity] (get-token* entity)))

(def app (json-web-app app-routes))