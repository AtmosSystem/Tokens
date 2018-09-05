(defproject atmos-tokens "0.1.0-SNAPSHOT"
  :description "Atmos micro service for manage atmos_tokens"
  :url "https://github.com/AtmosSystem/Tokens"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [atmos-kernel "0.6.15"]
                 [clj-google "0.2.6"]
                 [atmos-data-kernel "0.5.7"]
                 [environ "1.1.0"]]
  :plugins [[lein-ring "0.12.3"]
            [lein-environ "1.1.0"]]
  :ring {:handler atmos-tokens.api/app}
  :profiles {
             :uberjar {:aot :all
                       :env {:resource-file "config-prod"}}
             :dev     {:dependencies [[javax.servlet/servlet-api "2.5"]
                                      [ring/ring-mock "0.3.0"]]
                       :env          {:resource-file "config-test"}}
             :test    {:env {:resource-file "config-test"}}})