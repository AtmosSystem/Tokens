(ns atmos-tokens.core)

(defprotocol ITokenProtocol
  (generate-token [data])
  (validate-token [data]))

