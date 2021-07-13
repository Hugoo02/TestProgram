package ipca.appscore.a20126

import org.json.JSONObject

class App {

    var name             : String? = null
    var points           : Int? = null
    var description      : String? = null

    constructor() {

    }

    constructor(name: String?, points: Int?, description: String?) {
        this.name            = name
        this.points          = points
        this.description     = description
    }

    fun toJson() : JSONObject {
        val jsonObject = JSONObject()

        jsonObject.put("name", name)
        jsonObject.put("points", points.toString())
        jsonObject.put("description", description)

        return jsonObject
    }

    companion object {

        fun fromJson(jsonObject: JSONObject) : App {
            val app = App()

            app.name  = if (!jsonObject.isNull("name")) jsonObject.getString("name") else null
            app.points = if (!jsonObject.isNull("points")) jsonObject.getInt("points")else null
            app.description  = if (!jsonObject.isNull("description")) jsonObject.getString("description") else null

            return app
        }
    }

}