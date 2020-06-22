(defproject datomic-up-running "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.datomic/datomic-free "0.9.5697"]
                 [expectations "1.4.53"]]
  :main ^:skip-aot datomic-up-running.core
  :target-path "target/%s"
  :datomic {:schemas ["resources/datomic" ["schema.edn"]]}
  :profiles {:dev
             {:datomic {:config "resources/datomic/free-transactor-template.properties"
                        :db-uri "datomic:free://localhost:4334/pet-owners-db"}}})
