(defproject atmos-tokens "2.2"
  :description "Atmos micro service for manage tokens"
  :url "https://github.com/AtmosSystem/Tokens"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [atmos-kernel "2.1"]
                 ; Logs
                 [org.clojure/tools.logging "1.1.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 ; Web components
                 [atmos-web-kernel-reitit "0.1.0"]]
  :repositories [["releases" {:url           "https://clojars.org/repo"
                              :username      :env/CLOJAR_USERNAME
                              :password      :env/CLOJAR_PASSWORD
                              :sign-releases false}]])
