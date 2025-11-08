
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import TodoList from './components/todoList';
function App() {
  return (
    <Router>
      <div className="container mt-3">
        <Routes>
          <Route path="/api/v1/todo" element={<TodoList />} />
        </Routes>
      </div>
    </Router>
  );
}
export default App;
