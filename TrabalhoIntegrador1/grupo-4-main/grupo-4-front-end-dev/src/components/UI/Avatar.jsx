const Avatar = ({ fullName }) => {
  const nameInitials = fullName
    .split(" ")
    .reduce((initials, partialName) => initials + partialName[0], "");

  return (
    <svg width="36" height="36">
      <circle cx="18" cy="18" r="16" fill="#554d43" />
      <text
        x="50%"
        y="50%"
        textAnchor="middle"
        fill="white"
        fontSize="16px"
        fontFamily="Arial"
        dy=".3em"
      >
        {nameInitials.slice(0, 2)}
      </text>
    </svg>
  );
};

export default Avatar;
