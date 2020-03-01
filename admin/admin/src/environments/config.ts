/**
 * Config file for hostname and port number of backend server and JWT authentication server
 */
export const config = {
  jwt_auth: {
    hostname: location.hostname,
    port: "8081"
  },
  backend: {
    hostname: location.hostname,
    port: "8080"
  }
};
