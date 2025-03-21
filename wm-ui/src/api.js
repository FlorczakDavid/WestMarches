import axios from 'axios';
const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    "Content-type": "application/json"
  }
});

const handleError = (error) => {
  /*
   * If request was made, but the status code 
   * of the server response falls outside 
   * the 2xx range.
   */
  if (error.response) {

    // A lookup table of different error messages
    const messages = {
      404: 'Resource not found',
      500: 'Server error. Please try again later.',
    };

    const errorMessage = 
      messages[error.response.status] || `Unexpected error: ${error.response.status}`;

    alert(errorMessage, { id: 'api-error' });
    console.error('Full error:', error);
    return;
  }

  // If request was made but no response received
  if (error.request) {
    alert('No response from server. Check your network connection.', { id: 'api-error' });
    console.error('Full error:', error);
    return;
  }

  // If error was triggered by something else
  alert('Error setting up the request', { id: 'api-error' });
  console.error('Full error:', error);
};

api.interceptors.response.use(
  (response) => {
    const successMessage =
      response.config.successMessage ||
      `${response.config.method.toUpperCase()} request successful`;

    alert(successMessage, {
      id: 'api-success',
    });

    return response;
  },
  (error) => {
    handleError(error);
    return Promise.reject(error);
  }
);

export default api;