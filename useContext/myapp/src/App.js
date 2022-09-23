import "./App.css";
import { useState, useEffect } from "react";

function App() {
  const [movies, setMovies] = useState();
  useEffect(() => {
    const fetchData = async () => {
      const result = await fetch("/api/movies");
      const jsonResult = await result.json();
      setMovies(jsonResult.movies);
    };
    try {
      fetchData();
    } catch (err) {
      console.log(err);
    }
  }, []);
  return (
    <div className="container">
      <div className="div row justify-content-center">
        <div className="col">
          <h1 className="fw-normal text-center my-3">Movies</h1>
          {movies?.length > 0 ? (
            <table className="table">
              <thead>
                <tr>
                  <td>id</td>
                  <td>name</td>
                  <td>year</td>
                </tr>
              </thead>
              <tbody>
                {movies.map(({ id, name, year }) => (
                  <tr key={id}>
                    <td>{id}</td>
                    <td>{name}</td>
                    <td>{year}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <h1>No Movies </h1>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
