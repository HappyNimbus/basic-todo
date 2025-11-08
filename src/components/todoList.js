import React, {useState, useEffect} from "react";
import {todoService} from "../services/api";
import {Link} from 'react-router-dom';

const TodoList = () => {

    const [todos, setTodos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTodos = async () => {
            try{
                const response = await todoService.getAllTodos();
                setTodos(response.data);
                setLoading(false);
            }catch(err){
                setError('Failed to fetch todos');
                setLoading(false);
                console.error(err)
            }
        };
        fetchTodos();
    }, []);
    
    if (loading) return <div>Loading...</div>;
    if (error) return <div className="alert alert-danger">{error}</div>;
    return(    
    <div className="container mt-4">
      <h2>Todos</h2>
      <Link to="/api/v1/todo" className="btn btn-primary mb-3">
        Add New Todo
      </Link>
      <div className="row">
        {todos.length === 0 ? (
          <p>No todos found</p>
        ) : (
          todos.map(todo => (
            <div className="col-md-4 mb-3" key={todo.id}>
              <div className="card">
                <div className="card-body">
                  <h5 className="card-title">{todo.name}</h5>
                  <p className="card-text">{todo.description}</p>
                  <Link to={`/todos/${todo.id}`} className="btn btn-info mr-2">
                    View Details
                  </Link>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};
export default TodoList;