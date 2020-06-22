(ns datomic-up-running.core-test
  (:require [expectations :refer :all]
            [datomic-up-running.core :refer :all]
            [datomic.api :as d]))

(defn create-empty-in-memory-db []
  (let [uri "datomic:mem://pet-owners-test-db"]
    (d/delete-database uri)
    (d/create-database uri)
    (let [conn (d/connect uri)
          schema (load-file "resources/datomic/schema.edn")]
      (d/transact conn schema)
      conn)))

(expect
  #{["John"]}
  (with-redefs [conn (create-empty-in-memory-db)]
    (do
      (add-pet-owner "John")
      (find-all-pet-owners))))

(expect
  #{["John"] ["Paul"] ["George"]}
  (with-redefs [conn (create-empty-in-memory-db)]
    (do
      (add-pet-owner "John")
      (add-pet-owner "Paul")
      (add-pet-owner "George")
      (find-all-pet-owners))))

(expect
  #{["Salt"]}
  (with-redefs [conn (create-empty-in-memory-db)]
    (do
      (add-pet-owner "John")
      (add-pet "Salt" "John")
      (find-pets-for-owner "John"))))

(expect
  #{["Martha"] ["Jet"]}
  (with-redefs [conn (create-empty-in-memory-db)]
    (do
      (add-pet-owner "John")
      (add-pet "Salt" "John")
      (add-pet "Pepper" "John")
      (add-pet-owner "Paul")
      (add-pet "Martha" "Paul")
      (add-pet "Jet" "Paul")
      (find-pets-for-owner "Paul"))))
