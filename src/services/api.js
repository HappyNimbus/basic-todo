import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api/v1/todo';
const api = axios.create({
    baseURL: API_BASE_URL,
    headers:{
        'Content-Type':'application/json'
    }
});

export const todoService = {
    getAllTodos: () => api.get('/all'),
    getOneTodo: () => api.get('/${id}'),
    addTodo: () => api.post(),
    updateTodo: () => api.put('/${id}'),
    deleteTodo: () => api.delete('/${id}')
};

export default api;