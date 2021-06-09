import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class ActivityEndPointHappyPath extends Specification {

    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://api.github.com/')
//        firstClientData = new FirstClientData()
    }

    def 'user can get events'() {
        when:
        def response = client.get(path: "events",
                headers: ["User-Agent": "StephenG453"],
                requestContentType: 'application/json'
        )

        System.out.println("data:\n" + response.data)
        System.out.println("headers:\n" + response.headers)
        then:
        assert response.status == 200: 'could not retrieve event'
    }


}
