import { createServer,Model } from "miragejs"


createServer({

    models: {
      movie: Model,
    },
  
    routes() {
      this.namespace = "api"
  
      this.get("/movies", (schema, request) => {
        return schema.movies.all()
      })
    },
  
    seeds(server) {
      server.create("movie", { name: "Inception", year: 2010 })
      server.create("movie", { name: "Interstellar", year: 2014 })
      server.create("movie", { name: "Dunkirk", year: 2017 })
      server.create("movie",{name: "test", year: "3000" })
    },
  })














// createServer({
//   routes() {
//     this.namespace = "api"

//     this.get("/movies", () => {
//       return {
//         movies: [
//           { id: 1, name: "Inception", year: 2010 },
//           { id: 2, name: "Interstellar", year: 2014 },
//           { id: 3, name: "Dunkirk", year: 2017 },
//         ],
//       }
//     })
//   },
// })