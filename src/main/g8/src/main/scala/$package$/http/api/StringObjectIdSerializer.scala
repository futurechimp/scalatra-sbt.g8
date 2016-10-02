package $package$.http.api.serializers

import org.bson.types.ObjectId
import org.json4s.{Serializer, Formats}
import org.json4s.JsonAST.{JValue, JString}
import org.json4s.reflect.TypeInfo

class StringObjectIdSerializer extends Serializer[ObjectId] {

  val ObjectIdClass = classOf[ObjectId]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ObjectId] = {
    case (TypeInfo(ObjectIdClass, _), JString(v)) => new ObjectId(v)
  }

  def serialize(implicit formats: Formats): PartialFunction[Any, JValue] = {
    case x: ObjectId => JString(x.toString)
  }
}

object StringObjectIdSerializer {
  def apply() = new StringObjectIdSerializer()
}

