(ns atmos-tokens.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ITokenProtocol generate-token validate-token)

(defatmos-record-protocol :Token '[generate-token validate-token])
