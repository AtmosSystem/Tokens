(in-ns 'atmos-tokens.implementation.core)

(defn- query-token
  [entity]
  (let [credentials (:credentials configuration)
        access-token (:token configuration)
        project-id (-> configuration :datastore :project-id)
        namespace (-> configuration :datastore :namespace)]
    (if-let [data (with-token credentials access-token
                              (query project-id token
                                     :namespace namespace
                                     :filters {:Entity [= entity]
                                               :Type   [= "micro-service"]}))]
      (let [data (first data)
            data-properties (:properties data)]
        (-> data-properties :Value :stringValue)))))