package com.alvinalexander.rssreader

object Driver_RssFeedAsString extends App {

    //test against NPR’s RSS feed as a string. this would be like if you
    //want to get the string using your own HTTP library, and then want to
    //parse the RSS XML with this library.
    val rssFeed = RssReader.getRssFeedFromFeedString(SampleData.sampleNprFeed)
    val stories = rssFeed.stories
    stories.foreach(println)

    val rssXml = RssReader.convertRssFeedStringToXmlElem(SampleData.sampleNprFeed)
    println("URL:   " + RssReader.getChannelUrl(rssXml))
    println("Title: " + RssReader.getChannelTitle(rssXml))
    println("Desc:  " + RssReader.getChannelDescription(rssXml))
    println("Date:  " + RssReader.getChannelLastBuildDate(rssXml))

}


