(ns atmos-tokens.service
  (:require [atmos-tokens.core :refer :all]))

(require [atmos-tokens.implementation.core :refer :all])

(defn get-token*
  [entity]
  (get-token entity))



