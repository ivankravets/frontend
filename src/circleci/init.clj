(ns circleci.init
  )

(defn -main []
  (try
    ;;(require 'circleci.swank)
    (require 'circleci.db)
    (require 'circleci.db.migrations)
    (require 'circleci.web)
    (require 'circleci.repl)
    (require 'circleci.logging)
    ;; (circleci.swank/init)
    (circleci.logging/init)
    (circleci.db/init)
    (circleci.db.migrations/init)
    (circleci.web/init)
    (circleci.repl/init)
    (catch Exception e
      (println "caught exception on startup:")
      (.printStackTrace e)
      (println "exiting")
      (System/exit 1))))