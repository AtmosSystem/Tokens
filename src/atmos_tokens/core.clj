(ns atmos-tokens.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ITokenProtocol get-token)

(defatmos-record-protocol :Token '[get-token])
