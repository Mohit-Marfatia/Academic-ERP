

import axios from "axios";

// Create an Axios instance
const apiClient = axios.create({
  baseURL: "http://localhost:8080/api/v1", // Base URL for your API
  headers: {
    "Content-Type": "application/json",
  },
});

// Login API function
export const login = async (email, password) => {
  try {
    const response = await apiClient.post("/employees", { email, password });
    return response.data; // Return the response data
  } catch (error) {
    // Handle and throw errors in a standard format
    const errorMessage =
      error.response?.data?.message || "Login failed. Please try again.";
    throw new Error(errorMessage);
  }
};