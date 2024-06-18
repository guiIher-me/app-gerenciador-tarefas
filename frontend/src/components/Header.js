import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { Link } from 'react-router-dom';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import IconButton from '@mui/material/IconButton';
import Avatar from '@mui/material/Avatar';

export default function Header() {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const openMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters className="d-flex align-items-center">
          <img src="/icon_taskflow.ico" alt="Logo" style={{ width: '24px', height: '24px', marginRight: '8px' }} />

          <Typography
            variant="h6"
            noWrap
            component="a"
            href="#app-bar-with-responsive-menu"
            sx={{
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            TASKFLOW
          </Typography>

          <Link to="/home" style={{ marginLeft: '20px', textDecoration: 'none' }}>
            <Typography variant="h6" noWrap component="span">
              Board
            </Typography>
          </Link>


          {/* Dropdown menu with Avatar */}
          <IconButton className="tf-account-nav" onClick={openMenu} aria-label="More options" aria-controls="menu" aria-haspopup="true">
            <Avatar alt="Profile" />
          </IconButton>
          <Menu
            id="menu"
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleClose}
            anchorOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            transformOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
          >
            <MenuItem component={Link} to="/account">
              <Typography variant="body2">Conta</Typography>
            </MenuItem>
            <MenuItem component={Link} to="/logout">
              <Typography variant="body2">Sair</Typography>
            </MenuItem>
          </Menu>
        </Toolbar>
      </Container>
    </AppBar>
  );
}