/* eslint-disable react/jsx-key */
/* eslint-disable react/react-in-jsx-scope */
import tipboxInfo from "../elements/TipboxInfo";

export const Tipbox = () => {


  const tipContent =
    tipboxInfo &&
    tipboxInfo.filter((tip) => {
      return tip.id === "tne";
    });

  const text =
    tipContent &&
    tipContent[0].content.map((el) => {
      return <p >{el}</p>;
    });

  return (
    <div key="" className="tipbox-container">
      <div className="title">{tipContent[0].title}</div>
      <div className="content">
        <div>
          <img src={tipContent.src} alt=""></img>
        </div>
        {text}
      </div>
    </div>
  );
};
