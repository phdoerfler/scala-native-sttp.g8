import sttp.client3.*
import sttp.client3.jsoniter.*

import com.github.plokhotnyuk.jsoniter_scala.macros.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

object RestClient:
  given codec: JsonValueCodec[Post] = JsonCodecMaker.make

  def main(args: Array[String]): Unit =
    val backend = CurlBackend()
    try {
      val url = uri"http://jsonplaceholder.typicode.com/posts/1"
      val response = basicRequest
        .get(url)
        .response(asJson[Post])
        .send(backend)
      response.body match {
        case Left(error) => println(s"Failed \$error")
        case Right(json) => println(s"Response: \$json")
      }
    } finally {
      backend.close()
    }
