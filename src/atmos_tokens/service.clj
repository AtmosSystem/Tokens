(ns atmos-tokens.service
  (:require [atmos-tokens.core :refer :all]))

(require '[atmos-tokens.implementation.core :refer :all])

(defn authfn
  [request auth-data]
  (let [username (:username auth-data)
        password (:password auth-data)]
    true))

(defn get-microservice-token*
  [entity]
  (get-microservice-token entity))



