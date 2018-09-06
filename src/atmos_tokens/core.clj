(ns atmos-tokens.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ITokenProtocol get-microservice-token)

(defatmos-record-protocol :Token '[get-microservice-token])