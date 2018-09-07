(defproject atmos-tokens "1.0"
  :description "Atmos micro service for manage tokens"
  :url "https://github.com/AtmosSystem/Tokens"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [atmos-kernel "0.6.31"]]
  :plugins [[lein-ring "0.12.3"]
            [lein-environ "1.1.0"]]
  :ring {:handler atmos-tokens.api/app}
  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies [[javax.servlet/servlet-api "2.5"]
                                      [ring/ring-mock "0.3.0"]]}}
  :repositories [["releases" {:url           "https://clojars.org/repo"
                              :username      :env/CLOJAR_USERNAME
                              :password      :env/CLOJAR_PASSWORD
                              :sign-releases false}]])