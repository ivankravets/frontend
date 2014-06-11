(ns frontend.api
  (:require [frontend.models.user :as user-model]
            [frontend.models.build :as build-model]
            [frontend.utils :as utils :include-macros true]
            [frontend.utils.vcs-url :as vcs-url]
            [goog.string :as gstring]
            [goog.string.format]))

(defn get-projects [api-ch]
  (utils/ajax :get "/api/v1/projects" :projects api-ch))

(defn get-usage-queue [build api-ch]
  (utils/ajax :get
              (gstring/format "/api/v1/project/%s/%s/%s/usage-queue"
                              (vcs-url/org-name (:vcs_url build))
                              (vcs-url/repo-name (:vcs_url build))
                              (:build_num build))
              :usage-queue
              api-ch
              :context (build-model/id build)))

(defn get-dashboard-builds [{:keys [branch repo org]} api-ch]
  (let [url (cond branch (gstring/format "/api/v1/project/%s/%s/tree/%s" org repo branch)
                  repo (gstring/format "/api/v1/project/%s/%s" org repo)
                  org (gstring/format "/api/v1/organization/%s" org)
                  :else "/api/v1/recent-builds")]
    (utils/ajax :get url :recent-builds api-ch)))
