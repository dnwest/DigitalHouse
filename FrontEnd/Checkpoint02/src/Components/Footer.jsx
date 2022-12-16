import styles from "./Footer.module.css";
import { useContext } from "react";
import { NavBarContext } from "../Components/NavBarContext";

const Footer = () => {
  const scrollToTop = () => {
    window.scrollTo(0, 0)
  }

  const { contextIsLight } = useContext(NavBarContext);

  return (
    <footer>
      <div className={styles.footerWrapper}>
        <button className={`btn btn-danger ${styles.top}`} onClick={scrollToTop}>Voltar para o topo</button>
        <div className={contextIsLight ? `navbar-light bg-light ${styles.footer}` : `navbar-dark bg-dark ${styles.footer}`}>
          <div className="container">
            <div className={`row`}>
              <div className="col-sm-12 col-lg-6">
                <img className={contextIsLight ? styles.dhLogo : styles.DhLogoiconsDark} src="/images/DH.png" alt='DH-logo' />
              </div>
              <div className={`col-sm-12 col-lg-6 ${contextIsLight ? styles.icons : styles.iconsDark}`}>
                <img src="/images/ico-facebook.png" alt="ícone do facebook" className={styles.icon} />
                <img src="/images/ico-instagram.png" alt="ícone do instagram" className={styles.icon} />
                <img src="/images/ico-whatsapp.png" alt="ícone do whatsapp" className={styles.icon} />
                <img src="/images/ico-tiktok.png" alt="ícone do tiktok" className={styles.icon} />
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer >
  )
}

export default Footer