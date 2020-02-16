package DemoTest

import firstpackage.FirstScala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class scantest extends  AnyFlatSpec with Matchers{

  "MyUtil"should "Divide by 2" in {
    FirstScala.dividable(10,5) should be (2)
  }

  "Second Case" should "Zero" in {
       FirstScala.dividable(5,10) should be (0)  //x,y x/y 10/5
  }

}
