package com.alvinalexander.rssreader

/**
 * an rss feed, like http://alvinalexander.com/rss.xml
 */
case class RssFeed(
    channelTitle: String,
    channelUrl: String,
    stories: Seq[RssFeedStory]
)

/**
 * each individual story inside an rss feed
 */
case class RssFeedStory(
    title: String,
    url: String,
    description: String
)

/**
 * used to describe a web page. in the case of an rss feed you
 * might say, "alvin alexander's rss feed is located at
 * http://alvinalexander.com/rss.xml"
 */
case class WebPage(
    description: String,
    url: String
)

