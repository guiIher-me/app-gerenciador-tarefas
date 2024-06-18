import React from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import { Button, Card, CardContent, CardMedia, Typography } from '@mui/material/';
import { HttpAuthToken } from "../http";

export default function Logout() {
  useNavigate();
  const location = useLocation();
  const message = location?.state?.optionalMessage || "Agradecemos por escolher TaskFlow!";

  HttpAuthToken.deleteToken();

  return (
    <div className="container">
      <div className="row">
        <Card className="tf-main-form col-md-4 mx-auto">

          <CardMedia
            component="img"
            className="tf-form-logo mx-auto m2"
            alt="logo TaskFlow"
            image="/logo2_taskflow.png"
          />

          <br/>
          
          <Typography textAlign="center">{message}</Typography>

          <CardContent>
            <div className="d-flex justify-content-center">
              <Button
                variant="contained"
                href="/auth/login"
                className="mt-3"
              >
                Login
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
