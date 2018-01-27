package com.alvinalexander.rssreader

object Driver_ReadRssUrl extends App {

    /**
      * I found out that the current approach does not handle redirects.
      * I started with an HTTP URL for NPR, and they really use HTTPS,
      * and this code throws an exception because NPR’s HTTP response is
      * a 301 error.
      */

    // test against NPR’s RSS feed url
    val nprFeedUrl = "https://www.npr.org/rss/rss.php?id=100"
    val rssFeed = RssReader.getRssFeedFromUrl(nprFeedUrl)
    val stories = rssFeed.stories
    stories.foreach(println)

    // if you want these attributes, you need to get the RSS feed
    // as a scala.xml.Elem instance
    val rssXml = RssReader.getXmlFromRssFeedUrl(nprFeedUrl)
    println("URL:   " + RssReader.getChannelUrl(rssXml))
    println("Title: " + RssReader.getChannelTitle(rssXml))
    println("Desc:  " + RssReader.getChannelDescription(rssXml))
    println("Date:  " + RssReader.getChannelLastBuildDate(rssXml))


}
