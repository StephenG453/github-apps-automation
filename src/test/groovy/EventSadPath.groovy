package groovy

import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import static groovyx.net.http.ContentType.*

class EventSadPath extends Specification {

    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://api.github.com/')
    }

    def 'user cant get events with invalid header authentication'() {
        when:
        def response = client.get(path: "events",
                headers: ["User-Agents": "StephenG453"],
                requestContentType: 'application/json'
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 403: 'invalidly retrieved events while using incorrect header'
        //can use Data Driven Testing to test multiple different incorrect headers for extensive coverage
    }

    def 'user cant get events with invalid header content type'() {
        when:
        def response = client.get(path: "events",
                requestContentType: TEXT,
                headers: ["User-Agent": "StephenG453"]
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 415: 'invalidly retrieved events while using incorrect content type'
        //can use Data Driven Testing to test multiple different incorrect content types for extensive coverage
    }

    def 'user cant get events for a network of repositories with invalid header authentication'() {
        when:
        def response = client.get(path: "networks/octocat/hello-world/events",
                headers: ["User-Agents": "StephenG453"],
                requestContentType: 'application/json'
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 403: 'invalidly retrieved events while using incorrect header'
        //can use Data Driven Testing to test multiple different incorrect headers for extensive coverage
    }

    def 'user cant get events for a network of repositories with invalid header content type'() {
        when:
        def response = client.get(path: "networks/octocat/hello-world/events",
                requestContentType: TEXT,
                headers: ["User-Agent": "StephenG453"]
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 415: 'invalidly retrieved events while using incorrect content type'
        //can use Data Driven Testing to test multiple different incorrect content types for extensive coverage
    }

    def 'user cant get events for a network of repositories while specifying an invalid owner'() {
        when:
        def response = client.get(path: "networks/ownerthatdoesntexist/hello-world/events",
                requestContentType: 'application/json',
                headers: ["User-Agent": "StephenG453"]
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 404: 'invalidly retrieved events while using owner that doesnt exist'
    }

    def 'user cant get events for a network of repositories while specifying an invalid repository'() {
        when:
        def response = client.get(path: "networks/octocat/repothatdoesntexist/events",
                requestContentType: 'application/json',
                headers: ["User-Agent": "StephenG453"]
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 404: 'invalidly retrieved events while using a repository that doesnt exist'
    }
}
