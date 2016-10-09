(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/core.logic "0.8.10"]
                  [org.clojure/tools.nrepl "0.2.12"]])

(deftask dev
  []
  (comp
    (watch :verbose true)
    (repl :server true)))

